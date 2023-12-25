package com.golosovalka.repository;

import com.golosovalka.model.Poll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository - указывает на то, что это интерфейс репозитория, который используется для доступа к данным
@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
    //@Optional - возвращает объект Optional, который может содержать либо объект Poll с указанным идентификатором, либо null
    //@findById - метод для поиска опроса по его идентификатору
    Optional<Poll> findById(Long pollId);
    //@Page - возвращает объект Page, который содержит список опросов, созданных пользователем с указанным идентификатором
    //@findByCreatedBy - метод для поиска опросов, созданных пользователем с указанным идентификатором
    Page<Poll> findByCreatedBy(Long userId, Pageable pageable);
    //@long - возвращает количество опросов, созданных пользователем с указанным идентификатором
    //@countByCreatedBy - метод для подсчета количества опросов, созданных пользователем с указанным идентификатором
    long countByCreatedBy(Long userId);
    //@List - возвращает список опросов с указанными идентификаторами
    //@findByIdIn - метод для поиска списка опросов по списку их идентификаторов
    List<Poll> findByIdIn(List<Long> pollIds);
    //@List - возвращает список опросов с указанными идентификаторами, отсортированный по указанному порядку сортировки
    //@findByIdIn - метод для поиска списка опросов по списку их идентификаторов и сортировки результатов
    List<Poll> findByIdIn(List<Long> pollIds, Sort sort);
}
