package ma.aymane.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.aymane.hospital.entities.RendezVous;

public interface RendezVousRepository extends JpaRepository<RendezVous, String> {
}
