import java.util.HashMap;
import java.util.Map;

public class Main {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] a = {810,990,1440,990,1350,1800,990,1350,1800,810,1170,1620,720,900,1080,
                1350,720,900,1080,1350,720,900,1080,1350,765,1080,1260,900,1170,1440,1980,
                945,1170,1440,1620,720,900,1170,1440,720,990,1260,720,990,1260,720,990,1260,
                720,900,1080,1440,900,120,1620,720,900,1080,1440,720,900,1080,1440,720,900,
                1080,1440,900,1080,1440,1080,1440,1620,990,1350,1530,810,990,1260,1620};
//        int[] b= {1,2};
//        System.out.println(twoSum(a, 2600));
        System.out.println(System.getProperty("sun.arch.data.model"));
        System.out.println(System.getProperty("java.class.path"));
//        System.out.println(Arrays.toString(twoSum(b, 3)));

    }
}
