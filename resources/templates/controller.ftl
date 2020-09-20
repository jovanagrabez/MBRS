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
public class ${class.name?cap_first}Controller {
    private final ${class.name?cap_first}Service ${class.name?uncap_first}Service;

    <#list class.FMLinkedProperty as property>
    <#if property.upper == 1>
    private final ${property.type?cap_first}Service ${property.type?uncap_first}Service;
    </#if>
    </#list>

    private void populateRelatedEntities(ModelMap model) {
    <#list class.FMLinkedProperty as property>
    <#if property.upper == 1>
        model.put("${u.plural(property.name?uncap_first)}", ${property.type?uncap_first}Service.getAll());
    </#if>
    </#list>
    }

    @GetMapping("/${u.plural(class.name?lower_case)}/new/")
    public String getNewForm(ModelMap model) {
        model.put("${class.name?uncap_first}", new ${class.name}());
        populateRelatedEntities(model);

        return "${class.name?uncap_first}Form";
    }

    @GetMapping("/${u.plural(class.name?lower_case)}/{id}/edit")
    public String getEditForm(ModelMap model, @PathVariable Integer id) {
        model.put("${class.name?uncap_first}", ${class.name?uncap_first}Service.getOne(id).get());
        populateRelatedEntities(model);

        return "${class.name?uncap_first}Form";
    }

    @RequestMapping(value = "/${u.plural(class.name?lower_case)}", method = RequestMethod.GET)
    public String getAll(ModelMap model) {
        model.put("${u.plural(class.name?uncap_first)}",${class.name?uncap_first}Service.getAll());
        return "${class.name?uncap_first}Overview";
    }

    @RequestMapping(value = "/${u.plural(class.name?lower_case)}/{id}", method = RequestMethod.GET)
    public String getOne(ModelMap model, @PathVariable Integer id) {
        model.put("${class.name?uncap_first}", ${class.name?uncap_first}Service.getOne(id).get());
        return "${class.name?uncap_first}Details";
    }

    @RequestMapping(value = "/${u.plural(class.name?lower_case)}/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        ${class.name?uncap_first}Service.delete(id);
        return "redirect:/${u.plural(class.name?lower_case)}";
    }

    @RequestMapping(value = "/${u.plural(class.name?lower_case)}", method = RequestMethod.POST)
    public String update${class.name}(ModelMap model, @Valid ${class.name} ${class.name?lower_case}, BindingResult result) {
        if (result.hasErrors()) {
            return "${class.name?uncap_first}";
        }
        ${class.name?uncap_first}Service.update(${class.name?lower_case});
        return "redirect:/${u.plural(class.name?lower_case)}";
    }

}



