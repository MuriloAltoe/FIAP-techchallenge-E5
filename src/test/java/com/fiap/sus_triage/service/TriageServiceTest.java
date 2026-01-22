package com.fiap.sus_triage.service;

import com.fiap.sus_triage.dto.TriageQueueDTO;
import com.fiap.sus_triage.dto.request.TriageRequestDTO;
import com.fiap.sus_triage.dto.response.TriageResponseDTO;
import com.fiap.sus_triage.entity.Patient;
import com.fiap.sus_triage.entity.Triage;
import com.fiap.sus_triage.enums.Gender;
import com.fiap.sus_triage.enums.RiskLevel;
import com.fiap.sus_triage.enums.TriageStatus;
import com.fiap.sus_triage.repository.PatientRepository;
import com.fiap.sus_triage.repository.TriageRepository;
import com.fiap.sus_triage.strategy.RiskLevelStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TriageServiceTest {

        @Mock
        private PatientRepository patientRepository;

        @Mock
        private TriageRepository triageRepository;

        @Mock
        private RiskLevelStrategy riskLevelStrategy;

        @InjectMocks
        private TriageService triageService;

        // createTriage tests
        @Test
        void shouldCreateTriageAndReturnResponse() {

                Patient patient = new Patient(1L, "John Doe", 60, Gender.M);

                TriageRequestDTO request = new TriageRequestDTO();
                request.setPatientId(1L);
                request.setProblem("Dor no peito");

                when(patientRepository.findById(1L))
                                .thenReturn(Optional.of(patient));

                when(riskLevelStrategy.classify(request))
                                .thenReturn(RiskLevel.RED);

                TriageResponseDTO response = triageService.createTriage(request);

                assertNotNull(response);
                assertEquals("John Doe", response.getPatientName());
                assertEquals(RiskLevel.RED, response.getRiskLevel());

                verify(triageRepository, times(1)).save(any(Triage.class));
        }

        @Test
        void shouldThrowExceptionWhenPatientNotFound() {

                TriageRequestDTO request = new TriageRequestDTO();
                request.setPatientId(99L);

                when(patientRepository.findById(99L))
                                .thenReturn(Optional.empty());

                assertThrows(
                                RuntimeException.class,
                                () -> triageService.createTriage(request));

                verify(triageRepository, never()).save(any());
        }

        // getPrioritizedQueue tests
        @Test
        void shouldReturnQueueOrderedByRiskAndWaitingTime() {

                Patient p1 = new Patient(1L, "Alice", 70, Gender.F);
                Patient p2 = new Patient(2L, "Bob", 40, Gender.M);
                Patient p3 = new Patient(3L, "Charlie", 30, Gender.M);

                Triage t1 = new Triage(
                                1L,
                                p1,
                                "Dor severa",
                                RiskLevel.YELLOW,
                                TriageStatus.WAITING,
                                LocalDateTime.now().minusMinutes(30));

                Triage t2 = new Triage(
                                2L,
                                p2,
                                "Falta de ar",
                                RiskLevel.RED,
                                TriageStatus.WAITING,
                                LocalDateTime.now().minusMinutes(5));

                Triage t3 = new Triage(
                                3L,
                                p3,
                                "Dor de cabeça leve",
                                RiskLevel.GREEN,
                                TriageStatus.WAITING,
                                LocalDateTime.now().minusMinutes(60));

                when(triageRepository.findByStatus(TriageStatus.WAITING))
                                .thenReturn(List.of(t1, t2, t3));

                List<TriageQueueDTO> queue = triageService.getPrioritizedQueue();

                assertEquals(3, queue.size());

                assertEquals("Bob", queue.get(0).getPatientName());
                assertEquals(RiskLevel.RED, queue.get(0).getRiskLevel());

                assertEquals("Alice", queue.get(1).getPatientName());
                assertEquals(RiskLevel.YELLOW, queue.get(1).getRiskLevel());

                assertEquals("Charlie", queue.get(2).getPatientName());
                assertEquals(RiskLevel.GREEN, queue.get(2).getRiskLevel());
        }

        // startTriage tests
        @Test
        void shouldStartTriageWhenStatusIsWaiting() {

                Triage triage = new Triage(
                                1L,
                                null,
                                "Chest pain",
                                RiskLevel.RED,
                                TriageStatus.WAITING,
                                LocalDateTime.now());

                when(triageRepository.findById(1L))
                                .thenReturn(Optional.of(triage));

                triageService.startTriage(1L);

                assertEquals(TriageStatus.IN_PROGRESS, triage.getStatus());
                verify(triageRepository, times(1)).save(triage);
        }

        @Test
        void shouldThrowExceptionWhenTriageNotFound() {

                when(triageRepository.findById(99L))
                                .thenReturn(Optional.empty());

                assertThrows(
                                RuntimeException.class,
                                () -> triageService.startTriage(99L));

                verify(triageRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenTriageIsNotWaiting() {

                Triage triage = new Triage(
                                2L,
                                null,
                                "Headache",
                                RiskLevel.GREEN,
                                TriageStatus.IN_PROGRESS,
                                LocalDateTime.now());

                when(triageRepository.findById(2L))
                                .thenReturn(Optional.of(triage));

                assertThrows(
                                RuntimeException.class,
                                () -> triageService.startTriage(2L));

                verify(triageRepository, never()).save(any());
        }

        // finishTriage tests
        @Test
        void shouldFinishTriageWhenStatusIsInProgress() {

                Triage triage = new Triage(
                                3L,
                                null,
                                "Abdominal pain",
                                RiskLevel.YELLOW,
                                TriageStatus.IN_PROGRESS,
                                LocalDateTime.now());

                when(triageRepository.findById(3L))
                                .thenReturn(Optional.of(triage));

                triageService.finishTriage(3L);

                assertEquals(TriageStatus.FINISHED, triage.getStatus());
                verify(triageRepository, times(1)).save(triage);
        }

        @Test
        void shouldThrowExceptionWhenFinishTriageNotFound() {

                when(triageRepository.findById(77L))
                                .thenReturn(Optional.empty());

                assertThrows(
                                RuntimeException.class,
                                () -> triageService.finishTriage(77L));

                verify(triageRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenTriageIsNotInProgress() {

                Triage triage = new Triage(
                                4L,
                                null,
                                "Low fever",
                                RiskLevel.GREEN,
                                TriageStatus.WAITING,
                                LocalDateTime.now());

                when(triageRepository.findById(4L))
                                .thenReturn(Optional.of(triage));

                assertThrows(
                                RuntimeException.class,
                                () -> triageService.finishTriage(4L));

                verify(triageRepository, never()).save(any());
        }

}
