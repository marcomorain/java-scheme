package com.marcomorain.scheme;

import java.util.Iterator;

abstract class  NoRemovalIterator<T> implements Iterator<T> {
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
