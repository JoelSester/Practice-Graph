/*
How do you find the missing number in a given integer array of 1 to 100?
 */

import java.util.Random;

public class MissingNumber {
    int missingNumber;
    int[] numberList = new int[99];
    Random rand = new Random();
    MissingNumberSolution solution = new MissingNumberSolution();

    public void runTests(){
        int userSolution = 0;
        for(int i = 0; i<1000; ++i) {
            generateList();
            userSolution=solution.solution(retrieveList());
            if(!reviewSolution(userSolution))
                System.out.println("Incorrect");
        }
        System.out.println("All Tests completed");
    }

    private void generateList(){
        missingNumber = rand.nextInt(100)+1;
        for (int i = 1; i <= 100; ++i){
            if(i!=missingNumber){
                if(i<missingNumber){
                    numberList[i-1]=i;
                }else {
                    numberList[i-2]=i;
                }
            }
        }
    }

    private void displayList(){
        for(int i = 0; i<numberList.length; ++i){
            System.out.print(numberList[i]+" ");
        }
        System.out.print('\n');
    }

    private int[] retrieveList(){
        return numberList;
    }

    private boolean reviewSolution(int userSolution){
        if(userSolution==missingNumber){
            return true;
        }
        return false;
    }
}
