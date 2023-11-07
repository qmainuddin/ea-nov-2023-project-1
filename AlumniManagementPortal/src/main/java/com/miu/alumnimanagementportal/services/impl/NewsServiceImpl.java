package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.common.Converter;
import com.miu.alumnimanagementportal.dtos.JobPostDto;
import com.miu.alumnimanagementportal.dtos.NewsDto;
import com.miu.alumnimanagementportal.entities.JobPost;
import com.miu.alumnimanagementportal.entities.News;
import com.miu.alumnimanagementportal.exceptions.DataAlreadyExistException;
import com.miu.alumnimanagementportal.exceptions.ResourceNotFoundException;
import com.miu.alumnimanagementportal.repositories.JobPostRepository;
import com.miu.alumnimanagementportal.repositories.NewsRepository;
import com.miu.alumnimanagementportal.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final Converter converter;
    @Override
    public void create(NewsDto newsDto) {
        Optional.ofNullable(newsDto.getId()).ifPresent(id -> {
            if (newsRepository.existsById(id)) {
                throw new DataAlreadyExistException("News with id " + id + " already exists");
            }
        });
        newsRepository.save(converter.convert(newsDto, News.class));
    }

    @Override
    public List<NewsDto> findAll() {
        return newsRepository.findAll().stream()
                .map(element -> converter.convert(element, NewsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto update(NewsDto newsDto, Long id) {
        return Optional.ofNullable(newsDto.getId()).map(entityId -> {
            if (!newsRepository.existsById(entityId)) {
                throw new ResourceNotFoundException("JobPost with id " + entityId + " not found");
            }
            return converter.convert(newsRepository.save(converter.convert(newsDto, News.class)), NewsDto.class);
        }).orElseThrow(() -> new ResourceNotFoundException("JobPost with id " + id + " not found"));
    }

    @Override
    public NewsDto getNewsById(Long id) {
        return Optional.ofNullable(id)
                .map(newsRepository::findById)
                .map(element -> converter.convert(element, NewsDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("News with id " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        if (!newsRepository.existsById(id)) {
            throw new ResourceNotFoundException("News with id " + id + " not found");
        }
        newsRepository.deleteById(id);
    }
}
