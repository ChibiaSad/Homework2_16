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

    public IntegerListImpl(Integer[] storage) {
        this.storage = storage;
        size = storage.length;
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) throw new RuntimeException();
        if (size == storage.length) {
            storage = Arrays.copyOf(storage, size + 1);
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
            storage = Arrays.copyOf(storage, size + 1);
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
        Integer removed = storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        size--;
        return removed;
    }

    @Override
    public boolean contains(Integer item) {
        sortSelection();
        return binarySearch(item);
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
            if (Objects.equals(storage[i], item)) {
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

    private void sortSelection() {
        for (int i = 0; i < storage.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < storage.length; j++) {
                if (storage[j] < storage[minIndex]) {
                    minIndex = j;
                }
            }
            int tpm = storage[minIndex];
            storage[minIndex] = storage[i];
            storage[i] = tpm;
        }
    }

    private boolean binarySearch(Integer item){
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
}
