package org.salandur.advent_of_code.day15;

import org.salandur.advent_of_code.common.Point;

public abstract class AbstractWorldElement extends Point implements WorldElement {
    AbstractWorldElement(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String toString() {
        return getIdentifier() + super.toString();
    }
}
