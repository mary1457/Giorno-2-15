package mariapiabaldoin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import mariapiabaldoin.dao.EventiDAO;
import mariapiabaldoin.entities.Evento;
import mariapiabaldoin.entities.TipoEvento;
import mariapiabaldoin.exceptions.NotFoundException;

import java.time.LocalDate;


public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        EventiDAO ed = new EventiDAO(em);

        Evento evento1 = new Evento("Compleanno", LocalDate.of(2003, 6, 21), "auguri", TipoEvento.PRIVATO, 10);
        Evento evento2 = new Evento("Mercatini", LocalDate.of(2024, 12, 23), "Natale", TipoEvento.PUBBLICO, 50);


        ed.save(evento1);
        ed.save(evento2);
        ed.delete(1);

        try {
            Evento fromDb = ed.getById(2);
            System.out.println(fromDb);


        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }


        em.close();
        emf.close();

    }
}
