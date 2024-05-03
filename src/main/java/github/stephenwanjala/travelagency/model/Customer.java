package github.stephenwanjala.travelagency.model;

import org.jetbrains.annotations.NotNull;

public record Customer(@NotNull int id, @NotNull String name,@NotNull String email) {}
