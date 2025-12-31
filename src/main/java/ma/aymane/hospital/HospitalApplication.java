package ma.aymane.hospital;

import ma.aymane.hospital.entities.*;
import ma.aymane.hospital.service.IHospitalService;
import ma.aymane.hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    CommandLineRunner start(IHospitalService hospitalService) {
        return args -> {
            // ---- Création de patients ----
            Stream.of("Mohamed", "Hassan", "Najat").forEach(name -> {
                Patient p = new Patient();
                p.setNom(name);
                p.setDateNaissance(new Date());
                p.setMalade(Math.random() > 0.5);
                hospitalService.savePatient(p);
            });

            // ---- Création de médecins ----
            Stream.of("Aymane", "Hanane", "Yasmine").forEach(name -> {
                Medecin m = new Medecin();
                m.setNom(name);
                m.setEmail(name.toLowerCase() + "@gmail.com");
                m.setSpecialite(Math.random() > 0.5 ? "Cardio" : "Dentiste");
                hospitalService.saveMedecin(m);
            });

            // ---- Création d’un rendez-vous ----
            Patient patientRDV = new Patient();
            patientRDV.setNom("Laila");
            patientRDV.setDateNaissance(new Date());
            patientRDV.setMalade(false);
            patientRDV = hospitalService.savePatient(patientRDV);

            Medecin medecinRDV = new Medecin();
            medecinRDV.setNom("Dr. Saad");
            medecinRDV.setEmail("saad@gmail.com");
            medecinRDV.setSpecialite("Dermato");
            medecinRDV = hospitalService.saveMedecin(medecinRDV);

            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setPatient(patientRDV);
            rendezVous.setMedecin(medecinRDV);
            RendezVous savedRDV = hospitalService.saveRendezVous(rendezVous);

            // ---- Création d’une consultation ----
            Consultation consultation = new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(savedRDV);
            consultation.setRapport("Rapport de la consultation");
            hospitalService.saveConsultation(consultation);

            System.out.println("✅ Données enregistrées avec succès !");
        };
    }
}
