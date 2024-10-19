package com.group21.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;

public class NoSelectionModel<T> extends MultipleSelectionModel<T> {

    @Override
    public ObservableList<T> getSelectedItems() {
        return FXCollections.observableArrayList(); // Return an empty list since no items can be selected
    }

    @Override
    public ObservableList<Integer> getSelectedIndices() {
        return FXCollections.observableArrayList(); // Return an empty list since no items can be selected
    }

    @Override
    public void selectIndices(int index, int... indices) {
        // Do nothing to prevent selection
    }

    @Override
    public void selectAll() {
        // Do nothing to prevent selection
    }

    @Override
    public void selectFirst() {
        // Do nothing to prevent selection
    }

    @Override
    public void selectLast() {
        // Do nothing to prevent selection
    }

    @Override
    public void clearSelection(int index) {
        // Do nothing to prevent selection
    }

    @Override
    public void clearSelection() {
        // Do nothing to prevent selection
    }

    @Override
    public boolean isSelected(int index) {
        return false; // Always return false since no item can be selected
    }

    @Override
    public void select(int index) {
        // Do nothing to prevent selection
    }

    @Override
    public void clearAndSelect(int arg0) {
        
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void select(T arg0) {
       
    }

    @Override
    public void selectNext() {
       
    }

    @Override
    public void selectPrevious() {
       
    }
}
