<template>
  <div id="app">
    <the-header />
    <router-view class="music-content"/>
        <song-audio></song-audio>
        <play-bar></play-bar>
    <scroll-top></scroll-top>
    <the-footer></the-footer>
    <the-aside></the-aside>
  </div>
</template>

<script>
import TheHeader from './components/TheHeader'
import ScrollTop from './components/scrollTop'
import TheFooter from './components/TheFooter'
import SongAudio from './components/SongAudio'
import PlayBar from './components/PlayBar'
import TheAside from './components/TheAside'

export default {
  name: 'App',
  components: {
    TheHeader,
    ScrollTop,
    TheFooter,
    SongAudio,
    PlayBar,
    TheAside
  }
}
</script>

<style  lang="scss" scoped>
@import './assets/css/app.scss';
</style>
