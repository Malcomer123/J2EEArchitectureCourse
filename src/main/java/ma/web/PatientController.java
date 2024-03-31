package ma.web;

import jakarta.validation.Valid;
import ma.repos.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import ma.models.Patient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PatientController {
    @Autowired
    private PatientRepo patientRepo;

    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }

    @GetMapping("/user/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "5") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword){
        Page<Patient> patients = patientRepo.findByNomContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listPatients", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/admin/deletePatient")
    public String delete(@RequestParam(name = "id") Long id){
        patientRepo.deleteById(id);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

    @PostMapping("/admin/save")
    public String save(@RequestParam(name = "id", defaultValue = "") Long id,
                       @Valid Patient patient,
                       BindingResult bindingResult,
                       @RequestParam(name = "keyword", defaultValue = "") String keyword,
                       @RequestParam(name = "page", defaultValue = "0") int page){
        if(bindingResult.hasErrors()) return "formPatients";
        if(id != null && id != 0) patient.setID(id);
        patientRepo.save(patient);
        return "redirect:/user/index?keyword="+keyword+"&page="+page;
    }

    @GetMapping("/admin/editPatient")
    public String editPatient(Model model, @RequestParam(name = "id") Long id, String keyword, int currentPage){
        Patient patient = patientRepo.findById(id).orElseThrow();
        model.addAttribute("patient", patient);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", currentPage);

        return "editPatient";
    }


}
