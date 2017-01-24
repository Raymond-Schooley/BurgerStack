import java.io.*;


public class StackMain {
	private StackMain(){}
	
	public static void main(final String[] theArgs) throws EmptyStackException, IOException {
	    testBurgerMaking();
//	    testStack();
//        testBurger();
    }

    /** Testing the MyStack class. @throws EmptyStackException */
    public static void testStack() throws EmptyStackException {
        MyStack<String> mySt = new MyStack<>();

        for (int i = 0; i < 10; i++) {
            mySt.push("HI");
        }
        System.out.println(mySt);

        MyStack<Integer> mySt1 = new MyStack<>();
        System.out.println(mySt1);

        while (mySt1.hasNext()) {
            mySt1.pop();
        }
    }

    /** Testing Burger class. */
    public static void testBurger() {
	    Burger testBurger = new Burger(true);
    }

    /** Testing Making a burger.  @throws IOException */
    public static void testBurgerMaking() throws IOException {
        FileReader inputStream = null;

        try {
            inputStream = new FileReader("customer.txt");
            BufferedReader bufferedStream = new BufferedReader(inputStream);
            String line;
            int count = 0;
            while ((line = bufferedStream.readLine()) != null) {
                System.out.print("Processing Order "+ count++ + ": ");
                System.out.println(line); // useful for debugging
                parseLine(line);
                System.out.println();
            }
            bufferedStream.close();
        } finally {}

//		testParse();
    }

    public static void testParse() {
        parseLine("Burger");
        parseLine("Baron Burger");
        parseLine("Double Chicken Burger with Cheese Ketchup");
        parseLine("Triple Veggie Baron Burger with no Veggies Mustard but Onions");
    }

    public static void parseLine(String line) {
        String[] words = line.split(" ");
        Burger burger;

//		for(int i = 0; i < words.length; i++) {
//			System.out.println("|"+ words[i]+"|");
//		}

        boolean baronBurger = words[0].equals("Baron") || words[Math.min(1, words.length-1)].equals("Baron") || words[Math.min(2, words.length-1)].equals("Baron");
        burger = new Burger(baronBurger);
//		System.out.println(baronBurger);
        if(words[0].equals("Double"))
            burger.addPatty();
        if(words[0].equals("Triple")){
            burger.addPatty();
            burger.addPatty();
        }

        boolean chicken = words[0].equals("Chicken")|words[Math.min(1, words.length-1)].equals("Chicken");
        if(chicken) burger.changePatties("Chicken");
        boolean veggie = words[0].equals("Veggie")||words[Math.min(1, words.length-1)].equals("Veggie");
        if(veggie) burger.changePatties("Veggie");

        int with = 0;
        int but = 0;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals("with")) with = i;
            if(words[i].equals("but")) but = i;
        }

        if(with > 0) {
            int end = words.length;
            if(but > 0) end = but;
            for(int i = with; i < end; i++) {
//				System.out.println(words[i]);
                boolean cat = isCategory(words[i]);
                if(cat){
                    if (baronBurger)
                        burger.removeCategory(words[i]);
                    else
                        burger.addCategory(words[i]);
                } else {
                    if (baronBurger)
                        burger.removeIngredient(words[i]);
                    else
                        burger.addIngredient(words[i]);
                }
            }
        }
        if(but > 0) {
            int end = words.length;
            for(int i = but; i < end; i++) {
//				System.out.println(words[i]);
                if (baronBurger)
                    burger.addIngredient(words[i]);
                else
                    burger.removeIngredient(words[i]);
            }
        }

        System.out.println(burger.toString());
    }

    public static boolean isCategory(String str) {
            return str.equals("Cheese")||str.equals("Veggies")||str.equals("Sauce");
    }
}


