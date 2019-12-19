package test.Builder;

import static test.Builder.NyPizza.Size.SMALL;
import static test.Builder.Pizza.Topping.*;

/**
 * 当构造方法参数过多时使用 builder 模式
 * builder 比 JavaBeans 更安全
 * 比使用伸缩构造方法（telescoping constructors）更容易读写
 */
public class Main {
    public static void main(String[] args) {
        NyPizza pizza = new NyPizza.Builder(SMALL)
                .addTopping(SAUSAGE).addTopping(ONION).build();
        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM).sauceInside().build();
    }
}
