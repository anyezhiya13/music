<template>
  <div id="app">
    <song-audio-vue></song-audio-vue>
    <router-view></router-view>
  </div>
</template>

<script>
import SongAudioVue from './components/SongAudio.vue'
export default {
  name: 'App',
  components: {
    SongAudioVue
  }
}
</script>

<style>
</style>
