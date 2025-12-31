package ma.aymane.hospital.service;



import ma.aymane.hospital.entities.Consultation;
import ma.aymane.hospital.entities.Medecin;
import ma.aymane.hospital.entities.Patient;
import ma.aymane.hospital.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}

