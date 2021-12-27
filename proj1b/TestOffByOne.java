import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void isequalChars(){
        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('x','y'));
        assertFalse(offByOne.equalChars('X','y'));
        assertFalse(offByOne.equalChars('Z', 'a'));
        assertTrue(offByOne.equalChars('@', 'A'));
        assertFalse(offByOne.equalChars('!', '?'));
    }

}
