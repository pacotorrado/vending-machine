package com.machines.vending.domain.commands.user;

import com.machines.vending.domain.models.User;
import com.machines.vending.infraestructure.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteUserCommandImpl implements DeleteUserCommand {
    private final UserRepository userRepository;

    @Override
    public void execute(final User user) {
        // TODO Check deposit
        userRepository.deleteById(user.getId());
    }
}
