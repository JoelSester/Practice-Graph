public class MissingNumberSolution {
    boolean [] presentList = new boolean[100];
    public int solution(int [] numberList){
        for (int i = 0; i<presentList.length; ++i)
            presentList[i] = false;
        for(int i = 0; i<numberList.length; ++i){
            presentList[numberList[i]-1]=true;
        }
        for(int i = 0; i< presentList.length; ++i){
            if(presentList[i]==false)
                return i + 1;
        }
        return 0;
    }
}
