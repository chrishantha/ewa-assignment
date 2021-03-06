package lk.ac.cmb.ucsc.mcs.ewa.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Doctor;
import lk.ac.cmb.ucsc.mcs.ewa.entity.Speciality;
import lk.ac.cmb.ucsc.mcs.ewa.exception.AuthenticationException;
import lk.ac.cmb.ucsc.mcs.ewa.repository.DoctorRepository;
import lk.ac.cmb.ucsc.mcs.ewa.repository.SpecialityRepository;

@Component("doctorService")
@WebService(endpointInterface = "lk.ac.cmb.ucsc.mcs.ewa.service.DoctorService")
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecialityRepository specialityRepository;

    public void authenticate(String username, String password) throws AuthenticationException {
        Doctor doctor = doctorRepository.findByUsername(username);
        if (doctor == null || !password.equals(doctor.getPassword())) {
            throw new AuthenticationException("Invalid Credentials");
        }
    }

    public List<Doctor> getDoctors() {
        List<Doctor> doctors = new ArrayList<Doctor>();
        for (Doctor doctor : doctorRepository.findAll()) {
            doctors.add(doctor);
        }
        return doctors;
    }

    public List<Doctor> findDoctorsByFirstName(String firstName) {
        return doctorRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    public List<Doctor> findDoctorsByLastName(String lastName) {
        return doctorRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public List<Doctor> findDoctorsByFirstNameAndLastName(String firstName, String lastName) {
        return doctorRepository.findByFirstNameContainingAndLastNameContainingAllIgnoreCase(firstName, lastName);
    }

    public List<Doctor> findDoctorsBySpeciality(long specialityId) {
        return doctorRepository.findBySpeciality(specialityId);
    }

    public List<Speciality> getSpecialities() {
        List<Speciality> specialities = new ArrayList<Speciality>();
        for (Speciality speciality : specialityRepository.findAll()) {
            specialities.add(speciality);
        }
        return specialities;
    }

    public List<Speciality> findSpecialities(String speciality) {
        return specialityRepository.findBySpecialityContainingIgnoreCase(speciality);
    }

}
