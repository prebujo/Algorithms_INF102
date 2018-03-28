public class FourSum {
    public static void main(String[] args){
        int[] b = {3,5,-8,1,2,-6,-10,20,13,-13,-30,20,40,100,-35};
        int show = fourSum(b);
        System.out.println("Svaret ble " + show);
    }
    public static int fourSum(int [] a){
        int al = a.length;
        int count = 0;
        int sum = 0;
        for(int i = 0; i < al ; i++){
            for(int j = 0; j < al; j++){
                for(int k = 0; k < al; k++){
                    for(int l = 0; l < al; l++){
                        sum = a[i] + a[j] + a[k] + a[l];
                        if (sum == 0){
                            count++;
                        }

                    }
                }
            }
        }
        return count;

    }
}
