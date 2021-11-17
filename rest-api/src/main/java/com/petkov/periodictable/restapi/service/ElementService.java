package com.petkov.periodictable.restapi.service;

public interface ElementService {

    ElementModel getElementByAtomicNumber(Long atomicNumber);

    ElementModel updateElement(ElementRequest elementRequest, Long atomicNumber);

    void deleteElement(Long atomicNumber);

    ElementListModel getElements(String search);

    ElementModel createElement(ElementModel elementModel);
}
