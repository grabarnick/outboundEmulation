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
            $session.push = pushback.link
            # log("Link: " + pushback.link)
        a: Link: {{$session.push}}

    state: Outbound
        event!: eventName
        a: Исходящее сообщение

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}

