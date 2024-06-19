package aga.repository;

import aga.service.domain.Rating;

public interface RatingRepository {

    Rating save (Rating rating);

    Rating findByIdOrThrow(RatingId id);
}
