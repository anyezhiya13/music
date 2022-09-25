<!-- eslint-disable vue/valid-v-bind -->
<template>
    <div class="song-audio">
        <audio id="player"
        :src = 'url'
        controls= "controls"
        preload="true"
        @canplay="startPlay"
        @ended="ended"
        ></audio>
    </div>
</template>
<script>
import { mapGetters } from 'vuex'
export default {
  name: 'song-audio',
  computed: {
    ...mapGetters([
      'id',
      'isPlay',
      'url'
    ])
  },
  watch: {
    // 监听播放还是暂停
    isPlay: function () {
      this.toggletPlay()
    }
  },
  methods: {
    // 获取链接后准备播放
    startPlay () {
      let player = document.querySelector('#player')
      // 开始播放
      player.play()
    },
    ended () {
      this.isPlay = false
    },
    // 开始暂停
    toggletPlay () {
      let player = document.querySelector('#player')
      if (this.isPlay) {
        player.play()
      } else {
        player.pause()
      }
    }
  }
}
</script>
<style>
.song-audio{
    display: none;
}
</style>
