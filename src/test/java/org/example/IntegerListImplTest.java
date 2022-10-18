package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class IntegerListImplTest {
    IntegerListImpl integerList;
    @BeforeEach
    public void setUp(){
        integerList = new IntegerListImpl();
        integerList.add(34);
        integerList.add(74);
        integerList.add(65);
        integerList.add(723);
    }


    @Test
    void addTest() {
        Integer[] expected1 = {34, 74, 65, 723};
        Integer[] expected2 = {34, 74, 65, 723, 43, 97};
        Integer[] expected3 = {34, 74, 65, 723, 43, 97, 97, 97, 97, 97, 97};

        assertThat(integerList.toArray())
                .isNotEmpty()
                .isEqualTo(expected1);

        integerList.add(43);
        integerList.add(97);

        assertThat(integerList.toArray())
                .isNotEmpty()
                .isEqualTo(expected2);

        integerList.add(97);
        integerList.add(97);
        integerList.add(97);
        integerList.add(97);
        integerList.add(97);
        assertThat(integerList.toArray())
                .isNotEmpty()
                .isEqualTo(expected3);
    }

    @Test
    void indexAddTest() {
        Integer[] expected = {34, 74, 92, 65, 723};
        Integer[] expected3 = {34, 74, 92, 65, 723, 97, 97, 97, 97, 97, 98};

        integerList.add(2, 92);

        assertThat(integerList.toArray())
                .isNotEmpty()
                .hasSize(5)
                .isEqualTo(expected);

        integerList.add(97);
        integerList.add(97);
        integerList.add(97);
        integerList.add(97);
        integerList.add(97);
        integerList.add(integerList.size(), 98);
        assertThat(integerList.toArray())
                .isNotEmpty()
                .isEqualTo(expected3);
    }

    @Test
    void setTest() {
        Integer[] expected = {34, 74, 65, 23};

        integerList.set(3, 23);
        assertThat(integerList.toArray())
                .isNotEmpty()
                .hasSize(4)
                .isEqualTo(expected);
    }

    @Test
    void removeTest() {
        Integer[] expected1 = {34, 65, 723};
        Integer[] expected2 = {34, 65};

        integerList.remove((Integer) 74);

        assertThat(integerList.toArray())
                .isNotEmpty()
                .isEqualTo(expected1);

        integerList.remove((Integer) 723);

        assertThat(integerList.toArray())
                .isNotEmpty()
                .isEqualTo(expected2);
    }

    @Test
    void indexRemoveTest() {
        Integer[] expected1 = {34, 65, 723};
        Integer[] expected2 = {34, 65};

        integerList.remove(1);

        assertThat(integerList.toArray())
                .isNotEmpty()
                .hasSize(3)
                .isEqualTo(expected1);

        integerList.remove(2);

        assertThat(integerList.toArray())
                .isNotEmpty()
                .hasSize(2)
                .isEqualTo(expected2);
    }

    @Test
    void containsTest() {
        assertThat(integerList.contains(65))
                .isTrue();
        assertThat(integerList.contains(29))
                .isFalse();
    }

    @Test
    void indexOfTest() {
        integerList.add(65);
        assertThat(integerList.indexOf(65))
                .isEqualTo(2);
    }

    @Test
    void lastIndexOfTest() {
        integerList.add(65);
        assertThat(integerList.lastIndexOf(65))
                .isEqualTo(4);
    }

    @Test
    void getTest() {
        assertThat(integerList.get(3))
                .isEqualTo(723);
        assertThat(integerList.get(1))
                .isEqualTo(74);
    }

    @Test
    void testEqualsTest() {
        IntegerListImpl other = new IntegerListImpl(30);
        other.add(34);
        other.add(74);
        other.add(65);
        other.add(723);

        assertThat(integerList.equals(other))
                .isTrue();

        other.add(43);

        assertThat(integerList.equals(other))
                .isFalse();

        integerList.clear();
        other.clear();

        assertThat(integerList.equals(other))
                .isTrue();
    }

    @Test
    void sizeTest() {
        assertThat(integerList.size())
                .isEqualTo(4);

        integerList.add(43);

        assertThat(integerList.size())
                .isEqualTo(5);

        integerList.remove((Integer) 43);

        assertThat(integerList.size())
                .isEqualTo(4);
    }

    @Test
    void isEmptyTest() {
        assertThat(integerList.isEmpty())
                .isFalse();

        integerList.clear();

        assertThat(integerList.isEmpty())
                .isTrue();
    }

    @Test
    void clearTest() {
        assertThat(integerList.size())
                .isEqualTo(4);

        integerList.clear();

        assertThat(integerList.size())
                .isEqualTo(0);
    }

    @Test
    void toArrayTest() {
        Integer[] expected = {34, 74, 65, 723};

        assertThat(integerList.toArray())
                .isNotEmpty()
                .isEqualTo(expected);
    }

    @Test
    void testToStringTest() {
        assertThat(integerList.toString())
                .isEqualTo("[34, 74, 65, 723]");
    }

    @Test
    void NullItemExceptionTest(){
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> integerList.add(null));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> integerList.add(1, null));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> integerList.remove(null));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> integerList.contains(null));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> integerList.indexOf(null));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> integerList.lastIndexOf(null));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> integerList.set(2, null));
    }

    @Test
    void IndexOutOfSizeExceptionTest() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> integerList.add(-1, null));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> integerList.remove(-1));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> integerList.set(-1, 83));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> integerList.get(-1));
    }
}