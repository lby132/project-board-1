package org.example.projectboard1.respository.querydsl;

import org.example.projectboard1.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface ArticleRepositoryCustom {

    Page<Article> findByHashtagNames(Collection<String> hashtagNames, Pageable pageable);
}
