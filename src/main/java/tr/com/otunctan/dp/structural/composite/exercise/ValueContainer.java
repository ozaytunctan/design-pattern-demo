package tr.com.otunctan.dp.structural.composite.exercise;

import java.util.*;
import java.util.function.Consumer;

interface ValueContainer extends Iterable<Integer> {

}

class SingleValue implements ValueContainer {
    public int value;

    // please leave this constructor as-is
    public SingleValue(int value) {
        this.value = value;
    }

    @Override
    public Iterator<Integer> iterator() {
        ValueContainer that = this;
        return that.iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        ValueContainer.super.forEach(action);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        ValueContainer that = this;
        return that.spliterator();
    }
}

class ManyValues extends ArrayList<Integer> implements ValueContainer {

}


class MyList extends ArrayList<ValueContainer> {
    // please leave this constructor as-is
    public MyList(Collection<? extends ValueContainer> c) {
        super(c);
    }

    public int sum() {
        Integer sum = 0;
        for (ValueContainer value : this) {
            for (Integer val : value) {
                sum += val;
            }
        }
        return sum;
    }
}