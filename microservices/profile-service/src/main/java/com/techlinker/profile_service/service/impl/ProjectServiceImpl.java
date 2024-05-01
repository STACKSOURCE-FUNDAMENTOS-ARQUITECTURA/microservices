package com.techlinker.profile_service.service.impl;

 import com.techlinker.profile_service.entities.Project;
 import com.techlinker.profile_service.repository.IProjectRepository;
 import com.techlinker.profile_service.service.IProjectService;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProjectServiceImpl implements IProjectService {

   private final IProjectRepository projectRepository;

    public ProjectServiceImpl(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    @Transactional
    public Project save(Project project) throws Exception {
        return projectRepository.save(project);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        projectRepository.deleteById(id);
    }

    @Override
    public List<Project> getAll() throws Exception {
        return  projectRepository.findAll();
    }

    @Override
    public Optional<Project> getById(Long id) throws Exception {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> findByDigitalProfileId(Long id) throws Exception {
        return projectRepository.findByDigitalProfileId(id);
    }
}
