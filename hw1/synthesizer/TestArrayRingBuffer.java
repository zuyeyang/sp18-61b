package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
        ArrayRingBuffer x = new ArrayRingBuffer(4);
        x.enqueue(33.1); // 33.1 null null  null
        assertEquals(33.1,x.peek());
        x.enqueue(44.8); // 33.1 44.8 null  null
        x.enqueue(2); // 33.1 44.8 null  null
        x.enqueue(3); // 33.1 44.8 null  null

        x.dequeue();     // 44.8  null (returns 33.1)
        assertEquals(44.8,x.peek());
        for (Object s : x){
            System.out.println(s);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
