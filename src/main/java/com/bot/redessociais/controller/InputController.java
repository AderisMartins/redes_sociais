package com.bot.redessociais.controller;

import com.bot.redessociais.client.SeleniumClient;
import com.bot.redessociais.model.Input;
import com.bot.redessociais.model.User;
import com.bot.redessociais.provider.SeleniumProvider;
import com.bot.redessociais.repository.InputRepository;
import com.bot.redessociais.service.SeleniumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class InputController {

    public InputRepository inputRepository;

    @Autowired
    public void initialize(InputRepository inputRepository) throws ExecutionException, InterruptedException {
        this.inputRepository = inputRepository;
    }

    public List<Input> getInputList() {
        return (List<Input>) inputRepository.findAllInputs();
    }

    public Input getInpuById(Long id) {
        return inputRepository.getInputById(id);
    }

    public void UpdateFlagProcess(Long id, Integer flag_process) {
        Input input = inputRepository.findById(id).orElse(new Input());
        input.setFlag_process(flag_process);
        inputRepository.save(input);
    }

}
