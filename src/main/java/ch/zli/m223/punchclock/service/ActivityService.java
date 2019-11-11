package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Activity;
import ch.zli.m223.punchclock.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    private ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity createActivity(Activity activity) {
        return activityRepository.saveAndFlush(activity);
    }

    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    public void deleteActivity(Long id) {
        var activity = activityRepository.findById(id).orElseThrow();
        activityRepository.delete(activity);
    }
}
