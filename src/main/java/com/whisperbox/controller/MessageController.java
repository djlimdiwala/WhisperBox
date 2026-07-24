package com.whisperbox.controller;

import com.whisperbox.dto.MessageResponse;
import com.whisperbox.dto.SendMessageRequest;
import com.whisperbox.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(
    name = "Messages",
    description = "Endpoints for sending, receiving and managing messages."
)
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

@Operation(
    summary = "Send a message",
    description = "Stores a message for another user."
)
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Message stored successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid request")
})
@PostMapping("/{userKey}")
public com.whisperbox.dto.ApiResponse send(
        @Parameter(
            description = "Sender user key (A or B)",
            example = "A"
        )
        @PathVariable String userKey,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Message payload",
            required = true
        )
        @Valid
        @RequestBody
        SendMessageRequest request) {

    service.send(userKey, request);

    return new com.whisperbox.dto.ApiResponse(
            true,
            "Stored successfully"
    );
}

@Operation(
    summary = "Read unread messages",
    description = "Returns unread messages and marks them as read."
)
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Messages returned"),
    @ApiResponse(responseCode = "400", description = "Invalid user")
})
    @GetMapping("/{receiver}")
    public List<MessageResponse> getMessages(
            @Parameter(
                description = "Receiver user key",
                example = "A"
            )
            @PathVariable String receiver) {

        return service.getMessages(receiver);
    }

    @Operation(
        summary = "Conversation history",
        description = "Returns the complete conversation between both users."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Conversation returned"),
        @ApiResponse(responseCode = "400", description = "Invalid user")
    })
    @GetMapping("/conversation/{userKey}")
    public List<MessageResponse> conversation(
        @Parameter(
            description = "User key",
            example = "A"
        )
        @PathVariable String userKey) {

    return service.getConversation(userKey);
    }

    @Operation(
        summary = "Read unread messages",
        description = "Returns unread messages only and marks them as read."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Unread messages returned"),
        @ApiResponse(responseCode = "400", description = "Invalid user")
    })
    @GetMapping("/unread/{receiver}")
        public List<MessageResponse> unread(
            @Parameter(
            description = "Receiver user key",
            example = "A"
        )
        @PathVariable String receiver) {

        return service.getUnreadMessages(receiver);
    }

}