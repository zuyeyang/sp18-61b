import static org.junit.Assert.*;

import org.junit.Test;


public class Fliktest {

    @Test
    public void flicktest(){
        int a = 1;
        int b = 1;
        boolean exp = a == b ;
        assertEquals(exp,Flik.isSameNumber(a,b));
    }
}
