package demo.string.test;

import java.util.Optional;

public class Java8Optional {
    public static void optionalExample(String str, Optional<String> optStr){
        System.out.println("String :"+str+" and Optional String :\t"+optStr.get() );
    }

    public static void main(String[] args) {
        optionalExample("StringTest", null);
    }
}
