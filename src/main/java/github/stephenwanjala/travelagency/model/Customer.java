package github.stephenwanjala.travelagency.model;

import org.jetbrains.annotations.NotNull;

public record Customer(@NotNull String id, @NotNull String firstName, @NotNull String lastName, @NotNull String email) {

}
