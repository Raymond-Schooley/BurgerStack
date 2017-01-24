import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/**
 * David Dean
 * 1/23/17
 */
public class Burger {

    private boolean isBaronBurger;
    private static Stack<String> cheeseStack = new Stack<>();
    private static Stack<String> meatStack = new Stack<>();
    private static List<String> topBurger;
    private static List<String> bottomBurger;
    private static List<String> mainBurger = new ArrayList<>();
    private String meatType;
    private List<String> sauceList;
    private List<String> veggieList;
    private List<String> cheeseList;
    private List<String> ingredientList = new ArrayList<>();
    private List<String> extraList = new ArrayList<>();
    private boolean isSauce;
    private boolean isCheese;
    private boolean isVeggie;

    private Burger() {
    }

    Burger(boolean myBaronBurger) {
        emptyStack();
        isBaronBurger = myBaronBurger;
        setIngredients(myBaronBurger);
        meatType = "Beef";
        buildMenu();
    }

    private void setIngredients(boolean myIngredients) {
        isSauce = myIngredients;
        isCheese = myIngredients;
        isVeggie = myIngredients;
    }

    private void buildMenu() {
        cheeseStack.push("Cheddar");
        cheeseStack.push("Mozzarella");
        cheeseStack.push("Pepperjack");
        topBurger = new ArrayList<>(Arrays.asList("Pickle", "Bun", "Mayonnaise", "Baron-Sauce",
                "Lettuce", "Tomato", "Onions"));
        bottomBurger = new ArrayList<>(Arrays.asList("Mushrooms", "Mustard", "Ketchup", "Bun"));
        sauceList = new ArrayList<>(Arrays.asList("Ketchup", "Mustard", "Baron-Sauce",
                "Mayonnaise"));
        veggieList = new ArrayList<>(Arrays.asList("Mushrooms", "Onions", "Tomato",
                "Lettuce", "Pickle"));
        cheeseList = new ArrayList<>(Arrays.asList("Cheddar", "Mozzarella",
                "Pepperjack"));
    }

    private void emptyStack() {
        while (!cheeseStack.isEmpty())       {cheeseStack.pop();}
        while (!meatStack.isEmpty())         {meatStack.pop();}
        mainBurger.clear();
    }

    private void buildBurger() {
        // Build the Burger in sequence.
        mainBurger.addAll(topBurger);
        while (!meatStack.isEmpty())        {mainBurger.add(meatStack.pop());}
        while (!cheeseStack.isEmpty())      {mainBurger.add(cheeseStack.pop());}
        mainBurger.add(meatType);
        mainBurger.addAll(bottomBurger);

        // Removing items if it is not a Baron-Burger or not listed in ingredients.
//        System.out.println(extraList);
        if (isSauce) {
            List<String> tempList = new ArrayList<>();
            for (int i = 0; i < extraList.size(); i++) {
                String element = extraList.get(i);
                if (!checkList(sauceList, element)) {
                    tempList.add(element);
                }
            }
//            System.out.println("TEMP: " + tempList);
            ingredientList.remove(tempList);
        } else {
            for (int i = 0; i < extraList.size(); i++) {
                String element = extraList.get(i);
//                System.out.println(checkList(sauceList, element));
                if (checkList(sauceList, element)) {
                    sauceList.remove(element);
                }
            }
//            System.out.println(sauceList);

            ingredientList.addAll(sauceList);
        }
        if (isVeggie) {
            List<String> tempList = new ArrayList<>();
            for (int i = 0; i < extraList.size(); i++) {
                String element = extraList.get(i);
                if (!checkList(veggieList, element)) {
                    tempList.add(element);
                }
            }
//            System.out.println("TEMP: " + tempList);
            ingredientList.remove(tempList);
        } else {
            for (int i = 0; i < extraList.size(); i++) {
                String element = extraList.get(i);
//                System.out.println(checkList(veggieList, element));
                if (checkList(veggieList, element)) {
                    veggieList.remove(element);
                }
            }
//            System.out.println("VEGS: " + veggieList);

            ingredientList.addAll(veggieList);
        }
        if (isCheese) {
            List<String> tempList = new ArrayList<>();

            for (int i = 0; i < extraList.size(); i++) {
                String element = extraList.get(i);
                if (!checkList(cheeseList, element)) {
                    tempList.add(element);
                }
            }
//            System.out.println("TEMP: " + tempList);
            ingredientList.remove(tempList);
        } else {
            for (int i = 0; i < extraList.size(); i++) {
                String element = extraList.get(i);
//                System.out.println(checkList(cheeseList, element));
                if (checkList(cheeseList, element)) {
                    sauceList.remove(element);
                }
            }
//            System.out.println(cheeseList);

            ingredientList.addAll(cheeseList);
        }
        if (!isBaronBurger) {
            ingredientList.add("Pickle");
        }
//        System.out.println("ING LIST : " + ingredientList);
        for (String element : ingredientList) {
            mainBurger.remove(element);
        }
//        System.out.println("Sauce : " + isSauce + "\nVeggie: " + isVeggie + "\nCheese: " + isCheese);
    }

    private static boolean checkList(List<String> myArray, String myString) {
        return myArray.contains(myString);
    }

//    private static void checkIngredientList(String myIngredient) {
//        if (checkList())
//    }

    /**
     * Add the meat.
     */
    public void addPatty() {
        meatStack.push(meatType);
    }

    /**
     * Empty the meat stack then replace it with the new meat.
     * @param patty
     */
    public void changePatties(String patty) {
        meatType = patty;
        int count = 0;
        while (!meatStack.isEmpty()) {
            count++;
            meatStack.pop();
        }
        while (count != 0) {
            count--;
            meatStack.push(meatType);
        }
//        System.out.println("Change to: " + patty);
    }

    public void addCategory(String word) {
        if (word.equalsIgnoreCase("Sauce")) {
            isSauce = !isSauce;
        } else if(word.equalsIgnoreCase("Veggies")) {
            isVeggie = !isVeggie;
        } else if (word.equalsIgnoreCase("Cheese")) {
            isCheese = !isCheese;
        }
    }

    public void removeCategory(String word) {
        addCategory(word);
    }

    public void addIngredient(String word) {
        extraList.add(word);
        //System.out.println("Add Product: " + word);
    }

    public void removeIngredient(String word) {
//        if (word.equalsIgnoreCase("no")) {
//            exclusiveBurger = true;
//        }
        ingredientList.add(word);
        //System.out.println("Remove Product: " + word);
    }

    public String toString() {
        buildBurger();
        return mainBurger.toString();
    }

}
