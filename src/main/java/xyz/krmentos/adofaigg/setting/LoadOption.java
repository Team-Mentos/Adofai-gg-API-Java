/*
 * Copyright (c) 2024, Team Mentos
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the <organization> nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package xyz.krmentos.adofaigg.setting;

import xyz.krmentos.adofaigg.LoadManager;

/**
 * 맵 데이터를 로드하는 옵션을 정의한 열거형입니다.
 *
 * @author Jongyeol
 * @see LoadManager
 */
public enum LoadOption {
    /**
     * 데이터를 불러올 때 마다 데이터를 받아옵니다.
     */
    LOAD_EVERY_ACTIVE,

    /**
     * 특정 시간 마다 데이터를 받아옵니다.
     */
    LOAD_FOR_TIME,

    /**
     * 데이터를 불러올 때 특정 시간이 지나면 데이터를 받아옵니다.
     */
    LOAD_ACTIVE_FOR_TIME,

    /**
     * 데이터를 단 한번 받아온 후 더 이상 받아오지 않습니다.
     */
    LOAD_ONLY_ONCE,

    /**
     * 데이터를 수동으로 받아옵니다.
     */
    NOT_AUTO_LOAD
}

