package com.petkov.periodictable.restapi.service.impl;

import com.petkov.periodictable.entities.Element;
import com.petkov.periodictable.repositories.ElementRepository;
import com.petkov.periodictable.restapi.service.ElementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
public class ElementServiceImpl implements ElementService {

    private final ElementRepository elementRepository;

    public ElementServiceImpl(final ElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }

    @Override
    public ElementModel getElementByAtomicNumber(Long atomicNumber) {
        log.debug("getElementByAtomicNumber BEGIN: atomicNumber: {}", atomicNumber);

        Optional<Element> element = elementRepository.findById(atomicNumber);

        ElementModel elementModel = null;

        if(element.isPresent()) {
            elementModel = convertElementToElementModel(element);
        }

        log.debug("getElementByAtomicNumber END");
        return elementModel;
    }

    @Override
    public ElementModel updateElement(ElementRequest elementRequest, Long atomicNumber) {
        log.debug("updateElement BEGIN:elementRequest: {}, atomicNumber: {}", elementRequest, atomicNumber);

        //Validation
        //validateElementRequest(elementRequest);

        final Element updateElement = convertElementRequestToElement(elementRequest);

        final Element updatedElement = elementRepository.save(updateElement);

        final ElementModel resultElementModel = convertElementToElementModel(updatedElement);

        log.debug("updateElement END");
        return resultElementModel;
    }

    @Override
    public void deleteElement(Long atomicNumber) {
        log.debug("deleteElement BEGIN: atomicNumber: {}", atomicNumber);

        elementRepository.deleteById(atomicNumber);

        log.debug("deleteElement END");
    }

    @Override
    public List<ElementModel> getElements(String search) {
        log.debug("getElements BEGIN: searchQuery: {}", search);
        List<ElementModel> elementModelList = Collections.emptyList();

        log.debug("getElements END: elements.size: {}", elementModelList.size());
        return null;
    }

    @Override
    public ElementModel createElement(ElementModel elementModel) {
        log.debug("createElement BEGIN");

        //Validation
        //validateElementModel(elementModel);

        final Element element = convertElementModelToElement(elementModel);

        final Element resultElement = elementRepository.save(element);

        final ElementModel resultElementModel = convertElementToElementModel(resultElement);

        log.debug("createElement END");
        return resultElementModel;
    }
}
