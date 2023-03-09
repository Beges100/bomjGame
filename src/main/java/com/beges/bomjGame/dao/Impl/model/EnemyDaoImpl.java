package com.beges.bomjGame.dao.Impl.model;


import com.beges.bomjGame.dao.abstracts.model.EnemyDao;
import com.beges.bomjGame.model.Enemy;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@NoArgsConstructor
public class EnemyDaoImpl implements EnemyDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Enemy getEnemyById(Long id) {
        return entityManager.find(Enemy.class,id);
    }
}
