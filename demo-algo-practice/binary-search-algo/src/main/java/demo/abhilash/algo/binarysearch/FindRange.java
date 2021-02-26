package demo.abhilash.algo.binarysearch;

public class FindRange {
    public static int [] findRange(int[] arr, int key){
        int[] result={-1, -1};
        result[0]=search(arr, key,false);
        if(result[0]!=-1){
            result[1]=search(arr, key, true);
        }
        return result;
    }

    public static int search(int[] arr, int key, boolean findMaxIndex){
        int keyIndex=-1;
        int start=0;
        int end = arr.length -1;
        while (start<=end){
            int mid= start+(end-start)/2;
            if(key<arr[mid]){
                end= mid-1;
            }else if(key >arr[mid]){
                start= mid+1;
            }else{
                keyIndex= mid;
                if(findMaxIndex){
                    start=mid+1;
                }else{
                    end=mid-1;
                }
            }
        }
        return keyIndex;
    }

    public static void main(String[] args) {
        int[] range1=findRange(new int[]{4,6,6,6,9}, 6);
        System.out.println("Range [0]: "+range1[0]+" Range [1] :"+range1[1]);
//        int[] range2=findRange(new int[]{1,3,8,10,15}, 10);
//        System.out.println("Range [0]: "+range2[0]+" Range [1] :"+range2[1]);
    }
}
