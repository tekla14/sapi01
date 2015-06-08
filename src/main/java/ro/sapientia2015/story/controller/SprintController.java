package ro.sapientia2015.story.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ro.sapientia2015.story.dto.SprintDTO;
import ro.sapientia2015.story.exception.NotFoundException;
import ro.sapientia2015.story.model.Sprint;
import ro.sapientia2015.story.service.CommentService;
import ro.sapientia2015.story.service.SprintService;
import ro.sapientia2015.story.service.StoryService;

@Controller
public class SprintController {

	@Resource
	private SprintService service;
	
	@Resource
	private StoryService storyService;
	
	@Resource
	private CommentService commentService;
	
	public static final String VIEW_LIST = "sprint/list";
	public static final String VIEW_ADD = "sprint/add";
	protected static final String VIEW_VIEW = "sprint/view";
	
	private static final String MODEL_ATTRIBUTE = "sprint";
	
	protected static final String REQUEST_MAPPING_LIST = "/";
    protected static final String REQUEST_MAPPING_VIEW = "/sprint/{id}";


	@RequestMapping(value = "/sprint/list", method = RequestMethod.GET)
	public String listSprints(Model model) {

		List<Sprint> sprints = service.findAll();
		model.addAttribute("sprints", sprints);
		return VIEW_LIST;
	}
	
    private String createRedirectViewPath(String requestMapping) {
        StringBuilder redirectViewPath = new StringBuilder();
        redirectViewPath.append("redirect:");
        redirectViewPath.append(requestMapping);
        return redirectViewPath.toString();
    }
    
	@RequestMapping(value = "/sprint/add", method = RequestMethod.GET)
	public String showForm(Model model) {

		SprintDTO sprint = new SprintDTO();
		model.addAttribute("sprint", sprint);
		return VIEW_ADD;
	}

	@RequestMapping(value = "/sprint/add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute(MODEL_ATTRIBUTE) SprintDTO dto, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			return VIEW_ADD;
		}
		
		service.add(dto);
		
		return createRedirectViewPath("/sprint/list");
	}
	
	@RequestMapping(value = REQUEST_MAPPING_VIEW, method = RequestMethod.GET)
    public String findById(@PathVariable("id") Long id, Model model) throws NotFoundException {
        Sprint found = service.findById(id);
        model.addAttribute(MODEL_ATTRIBUTE, found);
        return VIEW_VIEW;
    }
	
	@RequestMapping(value = "/sprint/{id}/add-comment", method = RequestMethod.GET)
	public String addCommentToSprint(@PathVariable("id") Long id, Model model) throws NotFoundException {
		Sprint found = service.findById(id);
		model.addAttribute(MODEL_ATTRIBUTE, found);
		return VIEW_VIEW;
	}
	
}
