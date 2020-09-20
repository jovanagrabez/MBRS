<#import "utils.ftl" as u>
package ${class.typePackage};
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uns.ftn.mbrs.model.*;

import uns.ftn.mbrs.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import uns.ftn.mbrs.service.*;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LandingPageController {
    private static final String LANDING_PAGE_NAME = "landingPage";

    @GetMapping("/")
    public String getLandingPage() {
        return LANDING_PAGE_NAME;
    }

}



