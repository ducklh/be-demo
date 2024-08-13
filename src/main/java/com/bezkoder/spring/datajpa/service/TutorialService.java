package com.bezkoder.spring.datajpa.service;

import java.util.List;
import com.bezkoder.spring.datajpa.model.Tutorial;
import com.bezkoder.spring.datajpa.model.dto.CreateTutorialDTO;
import com.bezkoder.spring.datajpa.model.dto.TutorialDTO;

public interface TutorialService {
    List<Tutorial> getAllTutorials(String title);
    Tutorial getTutorialById(long id);
    Tutorial createTutorial(CreateTutorialDTO tutorial, Long userId);
    Tutorial updateTutorial(long id, TutorialDTO tutorial, Long userId);
    void deleteTutorial(long id);
    void deleteAllTutorials();
    List<Tutorial> findByPublished();
}
