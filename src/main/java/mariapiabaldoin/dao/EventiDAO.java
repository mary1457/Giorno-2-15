package mariapiabaldoin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import mariapiabaldoin.entities.Evento;
import mariapiabaldoin.exceptions.NotFoundException;

public class EventiDAO {

    private final EntityManager entityManager;

    public EventiDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Evento newEvento) {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newEvento);
        transaction.commit();

        System.out.println("L' evento " + newEvento + " è stato salvato");

    }

    public Evento getById(long eventoId) {
        Evento found = entityManager.find(Evento.class, eventoId);
        if (found == null) throw new NotFoundException(eventoId);
        return found;
    }

    public void delete(long eventoId) {


        Evento found = this.getById(eventoId);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(found);
        transaction.commit();

        System.out.println("L' evento" + found + " è stato eliminato");

    }
}
