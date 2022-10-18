package org.example;

import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {
    private Integer[] storage;
    private int size;

    public IntegerListImpl(int length) {
        this.storage = new Integer[length];
    }

    public IntegerListImpl() {
        this.storage = new Integer[10];
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) throw new RuntimeException();
        if (size == storage.length) {
            grow();
        }
        storage[size++] = item;
        return storage[size - 1];
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index < 0 || index > size || item == null) {
            throw new RuntimeException();
        }

        if (size == storage.length) {
            grow();
        }

        if(index == size){
            storage[size++] = item;
            return storage[size - 1];
        }

        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return storage[index];
    }

    @Override
    public Integer set(int index, Integer item) {
        if (item == null) throw new RuntimeException();
        if (index >= 0 && index < size) {
            storage[index] = item;
            return storage[index];
        }
        throw new RuntimeException();
    }

    @Override
    public Integer remove(Integer item) {
        if (item == null) throw new RuntimeException();
        int index = indexOf(item);
        if (index == -1) {
            throw new RuntimeException();
        }
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException();
        }
        Integer removed = storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        size--;
        return removed;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] copy = toArray();
        sort(copy, 0, size - 1);
        return binarySearch(copy, item);
    }

    @Override
    public int indexOf(Integer item) {
        if (item == null) throw new RuntimeException();
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i], item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item == null) throw new RuntimeException();
        int index = -1;
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public Integer get(int index) {
        if (index >= 0 && index < size) {
            return storage[index];
        }
        throw new RuntimeException();
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";

        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(storage[i]);
            if (i == size - 1) {
                stringBuilder.append("]");
            } else {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    private void sort(Integer[] storage, int start, int end) {
        if(start < end){
            int key = storage[end];
            int j = start - 1;

            for (int i = start; i < end; i++) {
                if(storage[i] <= key){
                    j++;

                    int temp = storage[j];
                    storage[j] = storage[i];
                    storage[i] = temp;
                }
            }

            int temp = storage[end];
            storage[end] = storage[j + 1];
            storage[j + 1] = temp;

            sort(storage, start, j);
            sort(storage, j + 2, end);
        }
    }

    private boolean binarySearch(Integer[] storage, Integer item){
        int min = 0;
        int max = size;
        while(min <= max){
            int mid = (min + max) / 2;
            if(storage[mid].equals(item)){
                return true;
            }
            if(item > storage[mid]){
                min = mid + 1;
            }else{
                max = mid - 1;
            }
        }
        return false;
    }

    private void grow(){
        storage = Arrays.copyOf(storage, (int) (size * 1.5));
    }
}
