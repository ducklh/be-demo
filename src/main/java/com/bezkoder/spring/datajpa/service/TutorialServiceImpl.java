package com.bezkoder.spring.datajpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bezkoder.spring.datajpa.model.dto.CreateTutorialDTO;
import com.bezkoder.spring.datajpa.model.dto.TutorialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.datajpa.model.Tutorial;
import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.repository.TutorialRepository;
import com.bezkoder.spring.datajpa.repository.UserRepository;

@Service
public class TutorialServiceImpl implements TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Tutorial> getAllTutorials(String title) {
        List<Tutorial> tutorials = new ArrayList<>();
        if (title == null) {
            tutorialRepository.findAll().forEach(tutorials::add);
        } else {
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
        }
        return tutorials;
    }

    @Override
    public Tutorial getTutorialById(long id) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        return tutorialData.orElse(null);
    }

    @Override
    public Tutorial createTutorial(CreateTutorialDTO tutorial, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        Tutorial tutorialData = new Tutorial();
        tutorialData.setDescription(tutorial.getDescription());
        tutorialData.setTitle(tutorial.getTitle());
        if (user != null) {
            tutorialData.setUser(user);
            return tutorialRepository.save(tutorialData);
        }
        return null;
    }

    @Override
    public Tutorial updateTutorial(long id, TutorialDTO tutorial, Long userId) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            User user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                _tutorial.setUser(user);
            }
            return tutorialRepository.save(_tutorial);
        }
        return null;
    }

    @Override
    public void deleteTutorial(long id) {
        tutorialRepository.deleteById(id);
    }

    @Override
    public void deleteAllTutorials() {
        tutorialRepository.deleteAll();
    }

    @Override
    public List<Tutorial> findByPublished() {
        return tutorialRepository.findByPublished(true);
    }
}
