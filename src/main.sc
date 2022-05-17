require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Начнём.

    state: Pushback
        intent!: /привет
        script:
            var pushback = $pushgate.createPushback(
                $request.channelType,
                $request.botId,
                $request.channelUserId,
                "eventName",
                {}
            );
            log("Link: " + pushback.link) 

    state: Outbound
        event!: eventName
        a: Исходящее сообщение

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}

