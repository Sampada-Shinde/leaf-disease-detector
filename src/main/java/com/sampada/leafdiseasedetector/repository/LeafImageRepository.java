
package com.sampada.leafdiseasedetector.repository;

import com.sampada.leafdiseasedetector.entity.LeafImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeafImageRepository extends JpaRepository<LeafImage, Long> {
}