package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Activity;
import ch.zli.m223.punchclock.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    private ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Activity> getAllEntries() {
        return activityService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Activity createActivity(@Valid @RequestBody Activity activity) {
        return activityService.createActivity(activity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
    }
}
