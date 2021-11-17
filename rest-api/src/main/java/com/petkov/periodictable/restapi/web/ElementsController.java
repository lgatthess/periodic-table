package com.petkov.periodictable.restapi.web;

import com.petkov.periodictable.restapi.service.ElementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import static com.petkov.periodictable.restapi.Constants.URL_ELEMENTS;

@Api(value = "CRUD operations for elements of the periodic table.")
@RestController
@RequestMapping(URL_ELEMENTS)
public class ElementsController {

    private final ElementService service;

    public ElementsController(final ElementService service) {
        this.service = service;
    }

    @PostMapping()
    @ApiOperation(
            value = "API to create periodic element.",
            notes = "The API will create periodic element with the provided parameters."
    )
    public ElementResponse createElement(
            @ApiIgnore @AuthenticationPrincipal final UserDetails userDetails,
            @RequestBody final ElementRequest elementRequest) {

        final ElementModel elementModel = convertRequestToEventModel(elementRequest);
        return convertElementModelToElementResponse(service.createElement(elementModel));
    }

    @GetMapping(value = "/{atomicNumber}")
    @ApiOperation(
            value = "API to get a single element.",
            notes = "Retrieves the element details for the given atomic number."
    )
    public ElementResponse getElementByAtomicNumber(
            @ApiIgnore @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("atomicNumber") final Long atomicNumber) {

        return convertElementModelToElementResponse(service.getElementByAtomicNumber(atomicNumber));

    }

    @PutMapping(value = "/{atomicNumber}")
    @ApiOperation(
            value = "API to update single periodic element.",
            notes = "Updates the element details for the given atomic number."
    )
    public ElementResponse updateElement(
            @ApiIgnore @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody final ElementRequest elementRequest,
            @PathVariable("atomicNumber") final Long atomicNumber) {

        return convertElementModelToElementResponse(service.updateElement(elementRequest, atomicNumber));
    }

    @DeleteMapping(value = "/{atomicNumber}")
    @ApiOperation(
            value = "API to delete single periodic element.",
            notes = "Deletes the element for the given atomic number."
    )
    public void deleteElement(
            @ApiIgnore @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("atomicNumber") final Long atomicNumber) {
        service.deleteElement(atomicNumber);
    }

    @GetMapping
    @ApiOperation(
            value = "API to search periodic elements.",
            notes = "This API uses an RSQL search query to find matching" +
                    "element entries."
    )
    public ElementListResponse getElements(
            @ApiIgnore @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(value = "search") final String search) {

        return convertElementModelListToElementListResponse(service.getElements(search));
    }

}
