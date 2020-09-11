package ${class.typePackage};
import java.util.*;


import uns.ftn.mbrs.model.*;

import uns.ftn.mbrs.service.${class.name}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
public class ${class.name?cap_first}Controller {

@Autowired
protected ${class.name?cap_first}Service ${class.name?lower_case}Service;



@RequestMapping(value = "all${class.name}", method = RequestMethod.GET)
public String getAll${class.name}(ModelMap model) {
model.put("all${class.name}",${class.name?lower_case}Service.getAll());
return "${class.name?lower_case}";
}

@RequestMapping(value = "/one${class.name}", method = RequestMethod.GET)
public String getOne${class.name}(@RequestParam Long id) {
${class.name?lower_case}Service.getOne(id);
return "redirect:/${class.name?lower_case}";
}



@RequestMapping(value = "/delete${class.name}", method = RequestMethod.GET)
public String delete${class.name}(@RequestParam Long id) {
${class.name?lower_case}Service.delete(id);
return "redirect:/${class.name?lower_case}";
}

@RequestMapping(value = "/update${class.name}", method = RequestMethod.POST)
public String update${class.name}(ModelMap model, @Valid ${class.name} ${class.name?lower_case}, BindingResult result) {

if (result.hasErrors()) {
return "${class.name?lower_case}";
}
${class.name?lower_case}Service.update(${class.name?lower_case});
return "redirect:/${class.name?lower_case}";
}


@RequestMapping(value = "/add${class.name}", method = RequestMethod.POST)
public String add${class.name}(ModelMap model, @Valid ${class.name} ${class.name?lower_case}, BindingResult result) {

if (result.hasErrors()) {
return "todo";
}

${class.name?lower_case}Service.add(${class.name?lower_case});
return "redirect:/${class.name?lower_case}";
}

}


