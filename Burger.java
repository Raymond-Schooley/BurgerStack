import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/**
 * David Dean
 * Raymond Schooley
 * 1/23/17
 * Project 1
 *
 * Burger class to build the burger.
 */
public class Burger {

    /** Set if a baron burger will be made. */
    private boolean isBaronBurger;
    /** Cheese Stack. */
    private static Stack<String> cheeseStack = new Stack<>();
    /** Meat Stack. */
    private static Stack<String> meatStack = new Stack<>();
    /** Top half of the Burger in sequence to be built. */
    private static List<String> topBurger;
    /** Bottom half of the Burger in sequence to be built. */
    private static List<String> bottomBurger;
    /** The burger which will be outputted and displayed. */
    private static List<String> mainBurger = new ArrayList<>();
    /** Meat Stack. */
    private String meatType;
    /** List of sauce ingredients. */
    private List<String> sauceList;
    /** List of veggie ingredients. */
    private List<String> veggieList;
    /** List of cheese ingredients. */
    private List<String> cheeseList;
    /** List of all ingredients to remove from burger is complete. */
    private List<String> ingredientList = new ArrayList<>();
    /** List of extra ingredients. */
    private List<String> extraList = new ArrayList<>();
    /** Flag if the sauce stack will be used. */
    private boolean isSauce;
    /** Flag if the cheese stack will be used. */
    private boolean isCheese;
    /** Flag if the veggie stack will be used. */
    private boolean isVeggie;

    /**
     * Constructor to build a basic burger.
     */
    public Burger() {
        this(false);
    }

    /**
     * Constructor to build the burger.
     * @param myBaronBurger set whether if it is a baron burger.
     */
    Burger(boolean myBaronBurger) {
        emptyStack();
        isBaronBurger = myBaronBurger;
        setIngredients(myBaronBurger);
        meatType = "Beef";
        buildMenu();
    }

    /**
     * Set all the flags on the ingredient stacks.
     * @param myIngredients mark as true or false.
     */
    private void setIngredients(boolean myIngredients) {
        isSauce = myIngredients;
        isCheese = myIngredients;
        isVeggie = myIngredients;
    }

    /**
     * Create the stack for each category and ingredients to use when building the burger.
     */
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

    /**
     * Clear the stack.
     */
    private void emptyStack() {
        while (!cheeseStack.isEmpty())       {cheeseStack.pop();}
        while (!meatStack.isEmpty())         {meatStack.pop();}
        mainBurger.clear();
    }

    /**
     * This is where the burger is created.
     */
    private void buildBurger() {
        // Build the Burger in sequence.
        mainBurger.addAll(topBurger);
        while (!meatStack.isEmpty())        {mainBurger.add(meatStack.pop());}
        while (!cheeseStack.isEmpty())      {mainBurger.add(cheeseStack.pop());}
        mainBurger.add(meatType);
        mainBurger.addAll(bottomBurger);

        // Removing items if it is not a Baron-Burger or not listed in ingredients.
        // Check the Sauce List
        if (isSauce) {
            trueCategory(sauceList);
        } else {
            falseCategory(sauceList);
        }
        // Check the Veggie List
        if (isVeggie) {
            trueCategory(veggieList);
        } else {
            falseCategory(veggieList);
        }
        // Check the Cheese List
        if (isCheese) {
            trueCategory(cheeseList);
        } else {
            falseCategory(cheeseList);
        }
        // If it is not a BaronBurger then add the pickle to remove.
        if (!isBaronBurger) {
            ingredientList.add("Pickle");
        }
        // Removing all elements from the ingredient list from the main burger.
        for (String element : ingredientList) {
            mainBurger.remove(element);
        }
    }

    /**
     * If the category is true
     * @param categoryList send the category list.
     */
    private void trueCategory(List categoryList) {
        List<String> tempList = new ArrayList<>();
        for (int i = 0; i < extraList.size(); i++) {
            String element = extraList.get(i);
            if (!checkList(categoryList, element)) {
                tempList.add(element);
            }
        }
        ingredientList.remove(tempList);
    }

    /**
     * If the category is false
     * @param categoryList send the category list.
     */
    private void falseCategory(List categoryList) {
        for (int i = 0; i < extraList.size(); i++) {
            String element = extraList.get(i);
            if (checkList(categoryList, element)) {
                categoryList.remove(element);
            }
        }
        ingredientList.addAll(categoryList);
    }

    /**
     * Check the list.
     * @param myArray the category list.
     * @param myString the ingredient to check in in the list.
     * @return
     */
    private static boolean checkList(List<String> myArray, String myString) {
        return myArray.contains(myString);
    }

    /**
     * Add the patty to the stack.
     */
    public void addPatty() {
        meatStack.push(meatType);
    }

    /**
     * Empty the meat stack then replace it with the new meat.
     * @param patty the meat to change to.
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
    }

    /**
     * Which category to mark as true to include.
     * @param word the category list.
     */
    public void addCategory(String word) {
        if (word.equalsIgnoreCase("Sauce")) {
            isSauce = !isSauce;
        } else if(word.equalsIgnoreCase("Veggies")) {
            isVeggie = !isVeggie;
        } else if (word.equalsIgnoreCase("Cheese")) {
            isCheese = !isCheese;
        }
    }

    /**
     * Send back to the addCategory to reverse the category flag
     * @param word the category list.
     */
    public void removeCategory(String word) {
        addCategory(word);
    }

    /**
     * Add the ingredient to the extra list.
     * @param word the ingredient.
     */
    public void addIngredient(String word) {
        extraList.add(word);
    }

    /**
     * Removing ingredients.
     * @param word the ingredient.
     */
    public void removeIngredient(String word) {
        ingredientList.add(word);
    }

    /**
     * Build the burger and output to the console.
     * @return String of the array.
     */
    public String toString() {
        buildBurger();
        return mainBurger.toString();
    }
}
